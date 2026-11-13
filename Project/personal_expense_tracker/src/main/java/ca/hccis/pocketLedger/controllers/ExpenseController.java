package ca.hccis.pocketLedger.controllers;

import ca.hccis.pocketLedger.bo.PocketLedgerBO;
import ca.hccis.pocketLedger.dao.PersonalExpenseTrackerDAO;
import ca.hccis.pocketLedger.jpa.entity.Expense;
import ca.hccis.pocketLedger.repositories.ExpenseRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseRepository _er;

    @Autowired
    public ExpenseController(ExpenseRepository _er) {
        this._er = _er;
    }

    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @RequestMapping("")
    public String home(Model model) {
        Iterable<Expense> expenses = _er.findAll();
        model.addAttribute("expenses", expenses);
        return "expense/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        Optional expense = _er.findById(id);
        if (expense.isPresent()) {
            model.addAttribute("expense", expense.get());
            return "expense/add";
        }

        model.addAttribute("messageError", "Could not load the expenses");
        Iterable<Expense> expenses = _er.findAll();
        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new Expense());
        return "expense/list";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        Expense newExpense = new Expense();
        model.addAttribute("expense", newExpense);
        return "expense/add";
    }

    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("expense") Expense expense, BindingResult bindingResult) {
        boolean valid = true;
        if (!valid || bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - JH");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");;
            model.addAttribute("expense", expense);
            return "expense/add";
        }

        expense.setRemainingBudget(PocketLedgerBO.calculate(expense));

        _er.save(expense);
        return "redirect:/expense";
    }

//    @RequestMapping("/list/edit")
//    public String showCreateForm(Model model) {
//        ExpenseDto expenseForm = new ExpenseDto();
//
//        Iterable<Expense> expenses = _er.findAll();
//        ArrayList<Expense> tempList = new ArrayList<>();
//        for (Expense current : expenses) {
//            tempList.add(current);
//        }
//        expenseForm.setExpenses(tempList);
//        model.addAttribute("form", expenseForm);
//        return "expense/listedit";
//    }

//    @RequestMapping("/list/edit/submit")
//    public String listEditSubmit(@ModelAttribute ExpenseDto form, Model model) {
//
//        for (Expense current: form.getExpenses()){
//            current.setRemainingBudget(PocketLedgerBO.calculate(current));
//        }
//
//        _er.save(form.getExpenses());
//
//        ExpenseDto expenseForm = new ExpenseDto();
//
//        Iterable<Expense> expenses = _er.findAll();
//        ArrayList<Expense> tempList = new ArrayList<>();
//        for (Expense current: expenses) {
//            tempList.add(current);
//        }
//        expenseForm.setExpenses(tempList);
//        model.addAttribute("form", expenseForm);
//        return "expense/listedit";
//    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {

        _er.deleteById(id);

        model.addAttribute("messageSuccess", "Expense has been deleted");

        Iterable<Expense> expenses = _er.findAll();
        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new Expense());
        return "expense/list";
    }
}
